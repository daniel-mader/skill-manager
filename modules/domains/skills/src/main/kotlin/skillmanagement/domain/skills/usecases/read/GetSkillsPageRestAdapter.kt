package skillmanagement.domain.skills.usecases.read

import org.springframework.hateoas.PagedModel
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import skillmanagement.common.model.PageIndex
import skillmanagement.common.model.PageSize
import skillmanagement.common.stereotypes.RestAdapter
import skillmanagement.domain.skills.model.SkillResource
import skillmanagement.domain.skills.model.toResource

@RestAdapter
@RequestMapping("/api/skills")
internal class GetSkillsPageRestAdapter(
    private val getSkillsPage: GetSkillsPageFunction
) {

    @GetMapping
    fun get(
        @RequestParam(required = false) page: Int?,
        @RequestParam(required = false) size: Int?
    ): PagedModel<SkillResource> {
        val skills = getSkillsPage(query(page, size))
        return skills.toResource()
    }

    private fun query(page: Int?, size: Int?) =
        AllSkillsQuery(pageIndex = PageIndex.of(page), pageSize = PageSize.of(size))

}
