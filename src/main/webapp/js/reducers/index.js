import { combineReducers } from 'redux';

import availableLeagues from './availableLeagues'
import leagueTable from './leagueTable'
import teamPlayers from './teamPlayers';
import leagueMatches from './leagueMatches';
import homeAwayState from './homeAwayState';

export default combineReducers({
    availableLeagues,
    leagueTable,
    teamPlayers,
    leagueMatches,
    homeAwayState
})