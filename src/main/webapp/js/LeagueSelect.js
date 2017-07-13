import React, { Component } from 'react';
import { connect } from 'react-redux';

class LeagueSelect extends Component {
    leagueChanged(event) {
        let leagueId = $(event.target).find(":selected").data("leagueId");
        let matchDay = $(event.target).find(":selected").data("toursPlayed");

        console.log(leagueId + " " + matchDay);
    }

    render () {
        return (
            <div>
                <select id="leagueInfo" onChange={this.leagueChanged}>
                    {this.props.testStore.map((league, index) =>
                        <LeagueItem key={league.id} league={league} />
                    )}
                </select>
            </div>
        )
    }
}

class LeagueItem extends Component {
    render () {
        return (
            <option key={this.props.league.id} data-league-id={this.props.league.id}
                    data-tours-played={this.props.league.toursPlayed}>
                {this.props.league.name}
            </option>
        )
    }
}

export default connect(
        state => ({ testStore : state }),
        dispatch => ({})
)(LeagueSelect);