package br.com.magalzim.neoforum.mapper

import br.com.magalzim.neoforum.model.Topic
import br.com.magalzim.neoforum.view.TopicView
import org.springframework.stereotype.Component

@Component
class TopicViewMapper: Mapper<Topic, TopicView> {
    override fun map(t: Topic): TopicView {
        return TopicView(
            id = t.id,
            title = t.title,
            message = t.message,
            registerDate = t.registerDate,
            status = t.status,
            updateDate = t.updateDate
        )
    }
}