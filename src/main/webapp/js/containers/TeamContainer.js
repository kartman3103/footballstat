import { connect } from 'react-redux';

import Team from '../components/league/Team';
import { loadPlayers } from '../api/external-api';
import { selectTeam } from "../api/user-api";
import TeamSelector from './../logic/TeamSelector';

const mapStateToProps = (state, ownProps) => {
    return {
        currentSelectedTeam: state.currentSelectedTeam,
        team: ownProps.team,
        teamPlayers: state.teamPlayers,
        homeAwayState: ownProps.homeAwayState,
        position: ownProps.position,
        teamHistory : ownProps.teamHistory
    }
};

function changeSelectedTeam(team, teamPlayers, currentSelectedTeam) {
    const selector = new TeamSelector(team, teamPlayers, currentSelectedTeam);
    const selectedTeam = selector.getSelectedTeam();

    selectTeam(selectedTeam);

    if (selector.isFirstSelect()) {
        loadPlayers(selectedTeam);
    }
}

const mergeProps = (stateProps) => {
    const { currentSelectedTeam, teamPlayers, homeAwayState, position, team, teamHistory } = stateProps;

    return {
        currentSelectedTeam: currentSelectedTeam,
        team: team,
        homeAwayState: homeAwayState,
        position: position,
        teamHistory: teamHistory,
        onTeamClick: () => changeSelectedTeam(
            team,
            teamPlayers,
            currentSelectedTeam
        )
    }
};

const mapDispatchToProps = dispatch => ({});

const TeamContainer = connect(
    mapStateToProps,
    mapDispatchToProps,
    mergeProps
)(Team);

export default TeamContainer;