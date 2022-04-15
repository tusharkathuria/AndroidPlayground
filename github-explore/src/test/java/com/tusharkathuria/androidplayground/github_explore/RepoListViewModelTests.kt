package com.tusharkathuria.androidplayground.github_explore

import com.tusharkathuria.androidplayground.github_explore.data.repositories.GithubRepo
import com.tusharkathuria.androidplayground.github_explore.ui.models.UIGithubRepo
import com.tusharkathuria.androidplayground.github_explore.ui.models.UIGithubRepoList
import com.tusharkathuria.androidplayground.github_explore.ui.viewmodels.RepoListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class RepoListViewModelTests {

    val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Verify get repositories invoked`()  = runTest {
        val githubRepo: GithubRepo = mock()

        doAnswer {
            println("Invoked")
            UIGithubRepoList(emptyList())
        }.whenever(githubRepo).getTopRepos("android")

        val vm = RepoListViewModel(githubRepo)


        vm.refreshTopRepos()
        verify(githubRepo).getTopRepos("android")
    }

    @Test
    fun `Verify state data update`()  = runTest {
        val githubRepo: GithubRepo = mock()

        doAnswer {
            println("Invoked")
            UIGithubRepoList(listOf(UIGithubRepo(0,"", "", "", "")))
        }.whenever(githubRepo).getTopRepos("android")

        val vm = RepoListViewModel(githubRepo)

        vm.refreshTopRepos()
        assert(vm.uiState.value.uiGithubRepoList.list.size == 1)
    }

    @Test
    fun `Verify loading state update`()  = runTest {
        val githubRepo: GithubRepo = mock()

        doAnswer {
            println("Invoked")
            UIGithubRepoList(listOf(UIGithubRepo(0,"", "", "", "")))
        }.whenever(githubRepo).getTopRepos("android")

        val vm = RepoListViewModel(githubRepo)

        assert(vm.uiState.value.isLoading)
        vm.refreshTopRepos()
        assert(!vm.uiState.value.isLoading)
    }
}