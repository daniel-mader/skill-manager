package skillmanagement.domain.employees.get

import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import skillmanagement.domain.HttpAdapter
import skillmanagement.domain.employees.EmployeeResource
import skillmanagement.domain.employees.toResource
import java.util.*

@HttpAdapter
@RequestMapping("/api/employees/{id}")
class GetEmployeeByIdHttpAdapter(
    private val getEmployeeById: GetEmployeeById
) {

    @GetMapping
    fun get(@PathVariable id: UUID): ResponseEntity<EmployeeResource> {
        val employee = getEmployeeById(id)
        if (employee != null) {
            return ok(employee.toResource())
        }
        return noContent().build()
    }

}
