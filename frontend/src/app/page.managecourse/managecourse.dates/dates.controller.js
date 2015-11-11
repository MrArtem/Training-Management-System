(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('DatesController', DatesController);

    /** @ngInject */
    function DatesController($scope, $state, $stateParams, userAPI) {
        var vm = this;
        vm.changeTab = changeTab;

        function changeTab() {
            if($scope.courseInfo.isRepeating) {
                $state.transitionTo('managecourse.dates.repeat');
            }
            else {
                $state.transitionTo('managecourse.dates.manual');
            }
        }
    }
})();