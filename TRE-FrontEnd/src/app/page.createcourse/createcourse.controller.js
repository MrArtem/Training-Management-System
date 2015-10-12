(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('CreateCourseController', CreateCourseController);

    /** @ngInject */
    function CreateCourseController($scope) {
        var vm = this;
        vm.courseInfo = {};
        vm.saveData = saveData;
        $scope.tempDates = [];
        $scope.temp = {
            startDate: "",
            endDate: ""
        };
        $scope.courseInfo = vm.courseInfo;
        $scope.courseInfo.lessonList = [];
        $scope.courseInfo.tagList = [];
        $scope.courseInfo.repeatModel = {
            lessonList: []
        };

        function saveData() {
            if($scope.courseInfo.isRepeating) {
                for(var i = 0; i < 7; i++) {
                    var date = new Date();
                    date.setHours($scope.tempDates[i].hours);
                    date.setMinutes($scope.tempDates[i].minutes);
                    var time = date.getTime() - (new Date(date.getYear() + '-' + date.getMonth() + '-' + date.getDay() + " 00:00")).getTime();
                    $scope.courseInfo.repeatModel.lessonList.push({time: time, place: $scope.tempDates[i].place});
                    $scope.courseInfo.repeatModel.startDate = (new Date($scope.temp.startDate)).getTime();
                    $scope.courseInfo.repeatModel.endDate = new Date($scope.temp.endDate + " 23:59:59").getTime();
                }
            }

            console.log($scope.courseInfo);
        }
    }
})();