package com.github.ferumbot.specmarket.bots.models.entity.embeded

import com.github.ferumbot.specmarket.bots.models.entity.converters.BotStateConverter
import com.github.ferumbot.specmarket.bots.state_machine.state.BotState
import com.github.ferumbot.specmarket.bots.state_machine.state.UnRegisteredState
import com.github.ferumbot.specmarket.bots.state_machine.state.UnSupportedScreenState
import com.github.ferumbot.specmarket.models.entities.filter.Niche
import com.github.ferumbot.specmarket.models.entities.filter.Profession
import javax.persistence.*

@Embeddable
data class UserBotState(

    @Convert(converter = BotStateConverter::class)
    @Column(name = "current_bot_state", nullable = false)
    var currentState: BotState = UnRegisteredState,

    @ManyToOne(
        cascade = [CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH],
        fetch = FetchType.LAZY
    )
    @JoinColumn(name = "current_filter_profession_id", nullable = true)
    var currentProfessionFilter: Profession? = null,

    @ManyToOne(
        cascade = [CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH],
        fetch = FetchType.LAZY
    )
    @JoinColumn(name = "current_filter_niche_id", nullable = true)
    var currentNicheFilter: Niche? = null,

    @Column(name = "current_page_number")
    var currentPageNumber: Int? = null,

    @Column(name = "total_available_pages")
    var totalAvailablePages: Int? = null,
)
