(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('Step3Controller', Step3Controller);

    /** @ngInject */
    function Step3Controller($scope) {
        var vm = this;
        vm.openCustomTab = openCustomTab;

        function openCustomTab() {
            return $scope.courseInfo.isRepeating ? 'step4.repeat' : 'step4.manual';
        }
    }
})();