(function ($) {
    'use strict';
    const basePath = "/api";

    function getDateString(s, prefix) {
        const tmp = moment(s);
        const diffDays = moment().diff(tmp, 'days');
        const diffHours = moment.duration(moment().diff(tmp)).hours();
        if (diffDays > 30) {
            return `${prefix} on ${tmp.format('MMM D YYYY')}`;
        } else {
            return `${prefix} ${diffDays} day(s) and ${diffHours} hours ago`;
        }
    }
    function addError(error) {
        $("#errorContainer").show().append(`<p>${error}</p>`);
    }
    function clearErrors() {
        $("#errorContainer").hide().html('')
    }
    function showLoading() {
        $('#openRequestTable>tbody, #closedRequestTable>tbody').html(`<tr><td colspan="4" class="loading"><img src="img/loading.gif"></td></tr>`)
    }
    function getPullRequests(state) {
        showLoading();
        return $.ajax({
            url: basePath + "/pulls?state=" + state,
        });
    }
    window.merge = (id) => {
        clearErrors();
        showLoading();
        $.ajax({
            url: basePath + "/pulls/" + id,
            type: "POST"
        }).done(() => {
            loadPullRequests();
        }).fail(() => {
            addError("Unable to merge requests")
        });
    };
    function loadPullRequests() {
        clearErrors();
        getPullRequests("closed").done((closedRequests) => {
            $('#closedRequestTable>tbody').html(`
				${closedRequests.map(req => {
                return `<tr><td>${req.number}</td><td>${req.title}</td><td>${req.author}</td><td>${getDateString(req.closedDate, 'Closed')}</td><td></td></tr>`;
            }).join('')}
			`);
        }).fail(() => addError("Unable to load closed requests"));
        getPullRequests("open").done((openRequests) => {
            $('#openRequestTable>tbody').html(`
				${openRequests.map(req => {
                return `<tr><td>${req.number}</td><td>${req.title}</td><td>${req.author}</td><td>${getDateString(req.openDate, 'Opened')}</td><td><button class="btn btn-primary" onclick="merge('${req.number}')">Merge</button></td></tr>`;
            }).join('')}
			`);
        }).fail(() => addError("Unable to load open requests"));
    };
    $(loadPullRequests());
})(jQuery);