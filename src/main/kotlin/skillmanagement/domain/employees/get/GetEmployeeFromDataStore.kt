package skillmanagement.domain.employees.get

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.transaction.annotation.Transactional
import skillmanagement.domain.TechnicalFunction
import skillmanagement.domain.employees.Employee
import skillmanagement.domain.employees.EmployeeRowMapper
import java.util.UUID

@TechnicalFunction
class GetEmployeeFromDataStore(
    private val jdbcTemplate: NamedParameterJdbcTemplate
) {

    private val employeeQuery = "SELECT * FROM employees WHERE id = :id"

    @Transactional(readOnly = true)
    operator fun invoke(id: UUID): Employee? =
        jdbcTemplate.query(employeeQuery, mapOf("id" to "$id"), EmployeeRowMapper).firstOrNull()

}
