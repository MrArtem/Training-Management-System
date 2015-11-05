(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('Step1Controller', Step1Controller);

    /** @ngInject */
    function Step1Controller(userAPI) {
        var vm = this;
        vm.coachInfo = {};
        vm.exCoachList = [];

        vm.addExCoach = addExCoach;
        vm.getExCoachList = getExCoachList;
        vm.setExCoach = setExCoach;
        vm.setMyselfAsCoach = setMyselfAsCoach;

        function addExCoach() {
            userAPI.addExCoach(vm.coachInfo).then(function(result) {
                //save coach //TODO
                console.log('Coach added successfully');
            });
        }

        function getExCoachList() {
            courseAPI.getExCoachList().then(function(data) {
                vm.exCoachList = angular.copy(data);
            });
        }

        function setExCoach(index) {
            var coachId = vm.exCoachList[index].id;
            $scope.$parent.courseInfo.coachId = coachId;
        }

        function setMyselfAsCoach() {
            $scope.$parent.courseInfo.coachId = authService.getUser().userId;
        }
    }
})();