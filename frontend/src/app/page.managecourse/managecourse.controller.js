(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('ManageCourseController', ManageCourseController);

    /** @ngInject */
    function ManageCourseController($scope, $stateParams, $location, courseAPI, userAPI, authService) {

        var vm = this;
        vm.exCoachList = [];
        vm.courseInfo = {};
        vm.courseInfo.tagList = []
        vm.exCoachInfo = {};

        vm.approveCourse = approveCourse;
        vm.createCourse = createCourse;
        vm.getCourseToEdit = getCourseToEdit;
        vm.getEditedCourse = getEditedCourse;
        vm.editCourse = editCourse;
        vm.isActive = isActive;
        vm.isAdmin = isAdmin;

        vm.addExCoach = addExCoach;
        vm.getExCoachList = getExCoachList;
        vm.setExCoach = setExCoach;
        vm.setMyselfAsCoach = setMyselfAsCoach;

        vm.getLanguage = getLanguage;
        vm.setLanguage = setLanguage;

        vm.getType = getType;
        vm.setType = setType;

        //Load external coaches
        if (vm.isAdmin()) {
            vm.getExCoachList();
        }

        if ($stateParams.edit) {
            vm.courseId = parseInt($stateParams.courseId);
            vm.actionId = parseInt($stateParams.id);
            $scope.isEdited = true;
            vm.isDraft = $stateParams.type == 'CREATE';

            if (vm.isDraft) {
                //if admin approves
                vm.getEditedCourse();
            }
            else {
                //if admin/user wants to edit
                vm.getCourseToEdit();

            }
        }
        else {
            $scope.isEdited = false;
            vm.courseInfo.isRepeating = false;
            vm.courseInfo.lessonList = [];
            vm.courseInfo.tagList = [];
            vm.courseInfo.repeatModel = {
                lessonList: []
            };
        }

        $scope.tempDates = [];
        $scope.temp = {
            startDate: "",
            endDate: ""
        };

        $scope.courseInfo = vm.courseInfo;

        ///////////////////////////////////////

        function approveCourse() {
            courseAPI.approveCourse(vm.actionId, $scope.courseInfo).then(function (result) {
                    //do something;
                }
            );
        }

        function createCourse() {
            console.log('course creating');
            if ($scope.courseInfo.isRepeating) {
                for (var i = 0; i < 7; i++) {
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
                    $scope.courseInfo.repeatModel.endDate = endDate.getTime();

                }
            }

            //if admin is logged, he must choose a coach for training
            if (!vm.isAdmin()) {
                $scope.courseInfo.coachId = authService.getUser().userId;
            }

            courseAPI.createCourse($scope.courseInfo).then(function (data) {
                console.log("request success")
            }); //some then with alert?

            console.log($scope.courseInfo);
        }

        function getCourseToEdit() {
            courseAPI.getShortInfo($stateParams.courseId).then(function (data) {
                $scope.courseInfo = angular.copy(data);
                courseAPI.getTimetable($stateParams.courseId).then(function (data) {
                    $scope.courseInfo.lessonList = angular.copy(data);
                    vm.coachName = $scope.courseInfo.coachName;
                    vm.isContentLoaded = true;
                    console.log('Received course: ', $scope.courseInfo);
                });
            });
        }

        function getEditedCourse() {
            courseAPI.getEditedCourse(vm.actionId).then(function (data) {
                    $scope.courseInfo = angular.copy(data);
                    vm.coachName = $scope.courseInfo.coachName;
                    console.log($scope.courseInfo);
                }
            );
        }

        function editCourse() {
            courseAPI.editCourse($stateParams.courseId, $scope.courseInfo).then(function(data) {
                //do something
            });
        }

        function isActive(state) {
            return $location.absUrl().search(state) === -1 ? false : true
        }

        function isAdmin() {
            return authService.getAccessRights() == 0 ? true : false;
        }

        ///////////////////////////////////////

        function addExCoach() {
            console.log('Coach to be added: ', vm.exCoachInfo);
            userAPI.addExCoach(vm.exCoachInfo).then(function (data) {
                vm.exCoachList = angular.copy(data);
                vm.exCoachInfo = {};
                console.log('Coach added successfully');
            });
        }

        function getExCoachList() {
            courseAPI.getExCoachList().then(function (data) {
                vm.exCoachList = angular.copy(data);
                console.log('Received coaches: ', vm.exCoachList);
            });
        }

        function setExCoach(index) {
            var coachId = vm.exCoachList[index].id;
            vm.coachName = vm.exCoachList[index].username;
            $scope.courseInfo.coachId = coachId;
        }

        function setMyselfAsCoach() {
            $scope.courseInfo.coachId = authService.getUser().userId;
            vm.coachName = 'me';
        }

        ///////////////////////////////////////

        function getLanguage(lang) {
            switch (lang) {
                case 1:
                    return 'English';
                case 2:
                    return 'Russian';
                default:
                    return 'unknown';
            }
        }

        function setLanguage(lang) {
            $scope.courseInfo.language = lang;
        }

        ///////////////////////////////////////

        function getType(type) {
            switch (type) {
                case true:
                    return 'Internal';
                case false:
                    return 'External';
                default:
                    return 'unknown';
            }
        }

        function setType(type) {
            $scope.courseInfo.isInner = type;
        }
    }
})();