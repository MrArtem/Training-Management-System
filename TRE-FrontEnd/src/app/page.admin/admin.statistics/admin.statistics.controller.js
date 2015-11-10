(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('StatisticsController', StatisticsController);

    /** @ngInject */
    function StatisticsController(adminAPI) {
        var vm = this;
        vm.staticticsModel = {};

        function downloadStatistics() {
            console.log('Statistics model to be uploaded: ', vm.statisticsModel);
            adminAPI.getStatistics(vm.statisticsModel).then(function(data) {
                $("#statDownload").attr("href", data.uri).attr("download", data.filename);
            });
        }

    }
})();