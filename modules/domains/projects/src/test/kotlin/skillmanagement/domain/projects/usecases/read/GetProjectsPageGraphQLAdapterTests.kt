package skillmanagement.domain.projects.usecases.read

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.slot
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest
import org.springframework.graphql.test.tester.GraphQlTester
import skillmanagement.common.model.PageIndex
import skillmanagement.common.model.PageSize
import skillmanagement.common.model.Pagination
import skillmanagement.common.model.emptyPage
import skillmanagement.common.model.pageOf
import skillmanagement.domain.projects.model.project_morpheus
import skillmanagement.domain.projects.model.project_neo
import skillmanagement.test.TechnologyIntegrationTest
import skillmanagement.test.graphql.AbstractGraphQlTest

@TechnologyIntegrationTest
@MockkBean(GetProjectsPageFunction::class)
@GraphQlTest(GetProjectsPageGraphQLAdapter::class)
internal class GetProjectsPageGraphQLAdapterTests(
    @Autowired override val graphQlTester: GraphQlTester,
    @Autowired val getProjectsPage: GetProjectsPageFunction
) : AbstractGraphQlTest() {

    @Test
    fun `translates and delegates retrieval to business function - default page`() {
        val slot = slot<AllProjectsQuery>()
        every { getProjectsPage(capture(slot)) } returns emptyPage()

        assertRequestResponse(
            documentPath = "/examples/graphql/getProjectsPage/default-page.graphql",
            responsePath = "/examples/graphql/getProjectsPage/default-page.json"
        )
        assertThat(slot.captured.pagination).isEqualTo(Pagination.DEFAULT)
    }

    @Test
    fun `translates and delegates retrieval to business function - page 1`() {
        val expectedQuery = AllProjectsQuery(Pagination(PageIndex(0), PageSize(10)))
        every { getProjectsPage(expectedQuery) } returns
                pageOf(listOf(project_neo, project_morpheus), index = 0, size = 10, totalElements = 2)

        assertRequestResponse(
            documentPath = "/examples/graphql/getProjectsPage/first-page.graphql",
            responsePath = "/examples/graphql/getProjectsPage/first-page.json"
        )
    }

    @Test
    fun `translates and delegates retrieval to business function - page 2`() {
        val expectedQuery = AllProjectsQuery(Pagination(PageIndex(1), PageSize(10)))
        every { getProjectsPage(expectedQuery) } returns
                emptyPage(index = 1, size = 10, totalElements = 2)

        assertRequestResponse(
            documentPath = "/examples/graphql/getProjectsPage/second-page.graphql",
            responsePath = "/examples/graphql/getProjectsPage/second-page.json"
        )
    }

}
