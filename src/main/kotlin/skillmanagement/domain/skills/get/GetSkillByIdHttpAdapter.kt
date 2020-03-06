package skillmanagement.domain.skills.get

import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.noContent
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import skillmanagement.domain.HttpAdapter
import skillmanagement.domain.skills.SkillResource
import skillmanagement.domain.skills.toResource
import java.util.UUID

@HttpAdapter
@RequestMapping("/api/skills/{id}")
class GetSkillByIdHttpAdapter(
    private val getSkillById: GetSkillById
) {

    @GetMapping
    fun get(@PathVariable id: UUID): ResponseEntity<SkillResource> {
        val skill = getSkillById(id)
        if (skill != null) {
            return ok(skill.toResource())
        }
        return noContent().build()
    }

}
