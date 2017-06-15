$(document).ready(function() {

    loadLeague($("#leagueInfo").find(":selected").data("leagueId"),
        $("#matchDay").find(":selected").val());

    $(document).on("change", "#leagueInfo", function() {
        var leagueId = parseInt($(this).find(":selected").data("leagueId"));
        var matchDay = parseInt($("#matchDay").find(":selected").val());
        loadLeague(leagueId, matchDay);
    });

    $(document).on("change", "#matchDay", function() {
        var leagueId = parseInt($("#leagueInfo").find(":selected").data("leagueId"));
        var matchDay = parseInt(this.value);

        loadLeague(leagueId, matchDay);
    });

    $(document).on("click", "[data-clickable-team]", function() {
        var teamId = $(this).data("clickableTeam");

        $.ajax({
            url: "/teamPlayers",
            type: "POST",
            dataType: "html",
            data: {
                teamId: teamId
            },
            success: function (response) {
                $("[data-players-container]").html(response);
            }
        });
    });

    function loadLeague(leagueId, matchDay) {
        $.ajax({
            url: "/league",
            type: "POST",
            dataType: "html",
            data: {
                leagueId : leagueId,
                matchDay : matchDay
            },
            success: function (response) {
                $("[data-league-container]").html(response);
            }
        });
    }
});