package skillmanagement.domain.skills.usecases.read

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import skillmanagement.common.model.Suggestion
import skillmanagement.common.searchindices.SearchIndex
import skillmanagement.common.stereotypes.HttpAdapter
import skillmanagement.domain.skills.model.Skill

@HttpAdapter
@RequestMapping("/api/skills/_suggest")
internal class SuggestSkillsHttpAdapter(
    private val searchIndex: SearchIndex<Skill>
) {

    @PostMapping
    fun post(
        @RequestParam(defaultValue = "100") size: Int,
        @RequestBody request: Request
    ): List<Suggestion> = searchIndex.suggest(input = request.input, size = size)

    data class Request(
        val input: String
    )

}
