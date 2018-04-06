package footballstat.database.dao.entity

import model.football.Player


class MongoTeam {
    var id : String? = null
        set
        get


    var Players : Collection<Player>? = null
        get
        set
}