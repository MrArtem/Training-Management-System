(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('ManageCourseController', ManageCourseController);

    /** @ngInject */
    function ManageCourseController($scope, $stateParams, courseAPI, authService) {

        var vm = this;
        vm.courseInfo = {};
        vm.createCourse = createCourse;
        vm.getEditedCourse = getEditedCourse;

        if($stateParams.edit) {
            vm.courseId = $stateParams.courseId;
            vm.isEdited = true;
            vm.isDraft = ($stateParams.type == 'CREATE') ? true : false;
            vm.getEditedCourse();
        }
        else {
            vm.isEdited = false;
        }

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

        function createCourse() {
            console.log('course creating');
            if($scope.courseInfo.isRepeating) {
                for(var i = 0; i < 7; i++) {
                    var date = new Date();
                    var fictiveDate = new Date();
                    date.setHours($scope.tempDates[i].hours);
                    fictiveDate.setHours(0);
                    date.setMinutes($scope.tempDates[i].minutes);
                    fictiveDate.setMinutes(0);
                    var time = date.getTime() - fictiveDate.getTime();
                    $scope.courseInfo.repeatModel.lessonList.push({date: time, place: $scope.tempDates[i].place});
                    $scope.courseInfo.repeatModel.startDate = (new Date($scope.temp.startDate)).getTime();
                    var endDate = new Date($scope.temp.endDate);
                    endDate.setHours(23);
                    endDate.setMinutes(59);
                    endDate.setSeconds(59);
                    $scope.courseInfo.repeatModel.endDate = endDate;

                }
            }
            $scope.courseInfo.language = parseInt($scope.courseInfo.language);
            $scope.courseInfo.coachId = authService.getUser().userId;
            courseAPI.createCourse($scope.courseInfo).then(function(data) {
                console.log("request success")
            }); //some then with alert?

            console.log($scope.courseInfo);
        }

        function getEditedCourse() {
            courseAPI.getEditedCourse(vm.courseId).then(function(data) {
                    $scope.courseInfo = angular.copy(data);
                    console.log($scope.courseInfo);
                }
            );
        }
    }
})();