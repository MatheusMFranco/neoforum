package br.com.magalzim.neoforum.view

object TopicByBoardViewTest {

    private fun build(name: String) = TopicByBoardView(name, 1)

    fun buildEveryone() = listOf(build("Plot"))
    fun buildPremium() = buildEveryone().plus(build("Premium"))
    fun buildStaff() = buildPremium().plus(build("Official"))

}