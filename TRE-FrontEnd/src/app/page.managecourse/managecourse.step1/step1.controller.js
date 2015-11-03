(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('Step1Controller', Step1Controller);

    /** @ngInject */
    function Step1Controller() {
        var vm = this;
        vm.exCoachList = [];
        vm.getExCoachList = getExCoachList;

        function getExCoachList() {
            courseAPI.getExCoachList().then(function(data) {
                vm.exCoachList = angular.copy(data);
            });
        }
    }
})();