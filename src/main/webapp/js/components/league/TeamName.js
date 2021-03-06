import React, {Component} from 'react';
import PropTypes from 'prop-types';

export default class TeamName extends Component {
    static imagePath (name) {
        return "/images/Clubs/" + name + ".svg";
    }

    render () {
        return (
            <div>
                <div className="club-logo">
                    <img src={TeamName.imagePath(this.props.teamName)} />
                </div>
                <div className="team-name">
                    <span>{this.props.teamName}</span>
                </div>
            </div>
        )
    }
}

TeamName.propTypes = {
    teamName : PropTypes.string
};