(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('Step4Controller', Step4Controller);

    /** @ngInject */
    function Step4Controller($scope, $state) {
        var vm = this;
        vm.manualDates = [];
        vm.dateTime = {};
        vm.repeat = {};
        vm.addDate = addDate;
        vm.changeTab = changeTab;

        vm.days = ['M', 'T', 'W', 'Th', 'F', 'Sa', 'S'];
        vm.onDays = [false, false, false, false, false, false, false];
        vm.dates = [];

        for(var i = 0; i < 7; i++) {
            $scope.tempDates.push({hours: 0, minutes: 0, place: ""});
        }

        function addDate() {
            $scope.courseInfo.lessonList.push((new Date(vm.dateTime.date + " " + vm.dateTime.hours + ":" + vm.dateTime.minutes)).getTime());
        }

        function changeTab() {
            if($scope.courseInfo.isRepeating) {
                $state.transitionTo('managecourse.step4.repeat');
            }
            else {
                $state.transitionTo('managecourse.step4.manual');
            }
        }
    }
})();