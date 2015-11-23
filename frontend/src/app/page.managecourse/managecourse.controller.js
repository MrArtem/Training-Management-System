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
        vm.courseInfo.tagList = [];
        vm.exCoachInfo = {};

        //Variables for form validations
        vm.coachClicked = false;
        vm.langClicked = false;
        vm.trySubmit = false;
        vm.typeClicked = false;

        //Variables for popovers
        vm.coachPopOpen = false;
        vm.langPopOpen = false;
        vm.typePopOpen = false;

        vm.approveCourse = approveCourse;
        vm.createCourse = createCourse;
        vm.cancelCourse = cancelCourse;
        vm.getCourseToEdit = getCourseToEdit;
        vm.getEditedCourse = getEditedCourse;
        vm.editCourse = editCourse;
        vm.isActive = isActive;
        vm.isAdmin = isAdmin;

        vm.addExCoach = addExCoach;
        vm.clearInfo = clearInfo;
        vm.getExCoachList = getExCoachList;
        vm.setExCoach = setExCoach;
        vm.setMyselfAsCoach = setMyselfAsCoach;

        vm.getLanguage = getLanguage;
        vm.setLanguage = setLanguage;

        vm.getType = getType;
        vm.setType = setType;

        //Something like form validators
        vm.areDatesSelected = areDatesSelected;
        vm.click = click;
        vm.isCoachSelected = isCoachSelected;
        vm.isLangSelected = isLangSelected;
        vm.isTypeSelected = isTypeSelected;
        vm.isFormValid = isFormValid;

        //Load external coaches
        if (vm.isAdmin()) {
            vm.getExCoachList();
        }

        if ($stateParams.edit) {
            vm.courseId = parseInt($stateParams.courseId);
            vm.actionId = parseInt($stateParams.id);
            $scope.isEdited = true;
            vm.isDraft = $stateParams.type === 'EDIT';

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
            vm.isDraft = true;
            //vm.courseInfo.isRepeating = false;
            vm.courseInfo.lessonList = [];
            vm.courseInfo.tagList = [];
            vm.courseInfo.repeatModel = {
                lessonList: []
            };
        }

        $scope.tempDates = [];
        for (var i = 0; i < 7; i++) {
            $scope.tempDates.push({});
        }

        $scope.temp = {
            onDays: [false, false, false, false, false, false, false],
            startDate: "",
            endDate: ""
        };

        $scope.courseInfo = vm.courseInfo;

        ///////////////////////////////////////

        function approveCourse() {
            vm.trySubmit = true;
            if (!isFormValid) {
                return;
            }
            courseAPI.approveCourse(vm.actionId, $scope.courseInfo).then(function (result) {
                    //do something;
                }
            );
        }

        function createCourse() {
            vm.trySubmit = true;

            if (!vm.isFormValid()) {
                return;
            }
            console.log('course creating');
            if ($scope.courseInfo.isRepeating) {
                $scope.courseInfo.place = null;
                var date = new Date();
                var fictiveDate = new Date();
                $scope.tempDates.forEach(function (lesson, index) {
                    if ($scope.temp.onDays[index]) {
                        date.setHours(lesson.hours);
                        fictiveDate.setHours(0);
                        date.setMinutes(lesson.minutes);
                        fictiveDate.setMinutes(0);
                        var time = date.getTime() - fictiveDate.getTime();
                        $scope.courseInfo.repeatModel.lessonList.push({date: time, place: lesson.place});
                    }
                    else {
                        $scope.courseInfo.repeatModel.lessonList.push(null);
                    }
                });
                $scope.courseInfo.repeatModel.startDate = (new Date($scope.temp.startDate)).getTime();
                var endDate = new Date($scope.temp.endDate);
                endDate.setHours(23);
                endDate.setMinutes(59);
                endDate.setSeconds(59);
                $scope.courseInfo.repeatModel.endDate = endDate.getTime();
            }

            //if admin is logged, he must choose a coach for training
            if (!vm.isAdmin()) {
                $scope.courseInfo.coachId = authService.getUser().userId;
            }

            console.log($scope.courseInfo);

            courseAPI.createCourse($scope.courseInfo).then(function (data) {
                console.log("request success");
            }); //some then with alert?
        }

        function cancelCourse(){
            courseAPI.cancelCreate(vm.actionId).then(function(result) {
                return result.data;
            });
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
            vm.trySubmit = true;
            if (!isFormValid) {
                return;
            }
            courseAPI.editCourse($stateParams.courseId, $scope.courseInfo).then(function (data) {
                //do something
            });
        }

        function isActive(state) {
            return $location.absUrl().search(state) !== -1;
        }

        function isAdmin() {
            return authService.getAccessRights() === 0;
        }

        ///////////////////////////////////////

        function click(model, popOpen) {
            model = !model;
            popOpen = !popOpen;
        }

        function isCoachSelected() {
            if (vm.isAdmin()) {
                return $scope.courseInfo.coachId ? true : false;
            }
            else {
                return true;
            }
        }

        function isLangSelected() {
            return $scope.courseInfo.language !== undefined;
        }

        function isTypeSelected() {
            return $scope.courseInfo.isInner !== undefined;
        }

        function areDatesSelected() {
            var isValid = true;
            if ($scope.courseInfo.isRepeating === undefined || $scope.courseInfo.isRepeating === null || !$scope.courseInfo.lessonList) {
                return false;
            }
            if ($scope.courseInfo.isRepeating) {
                if ($scope.temp.onDays.indexOf(true) == -1 || (new Date($scope.temp.startDate)).getTime() < (new Date()).getTime() || (new Date($scope.temp.endDate)).getTime() < (new Date()).getTime() || (new Date($scope.temp.endDate)).getTime() <= (new Date($scope.temp.startDate)).getTime()) {
                    return false;
                }

                $scope.tempDates.forEach(function (lesson, index) {
                    if ($scope.temp.onDays[index]) {
                        if (vm.isAdmin() && lesson.place == "") {
                            isValid = false;
                        }
                        if (lesson.hours < 0 || lesson.hours > 23 || lesson.hours != parseInt(lesson.hours, 10) || lesson.minutes < 0 || lesson.minutes > 59 || lesson.minutes != parseInt(lesson.minutes, 10)) {
                            isValid = false;
                        }
                    }
                });
            }
            else {
                if ($scope.courseInfo.lessonList.length == 0) {
                    return false;
                }
                $scope.courseInfo.lessonList.forEach(function (lesson) {
                    if (lesson.date < (new Date().getTime())) {
                        return false;
                    }
                })
            }
            return isValid;
        }

        function isFormValid() {
            return (vm.isCoachSelected() && vm.isLangSelected() && vm.isTypeSelected() && vm.areDatesSelected());
        }

        ///////////////////////////////////////

        function addExCoach() {
            console.log('Coach to be added: ', vm.exCoachInfo);
            userAPI.addExCoach(vm.exCoachInfo).then(function (data) {
                vm.exCoachList = angular.copy(data);
                console.log('Coach added successfully');
            });
        }

        function clearInfo() {
            $scope.coachForm.$setUntouched();
            vm.exCoachInfo = {};
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
            vm.coachPopOpen = false;
        }

        function setMyselfAsCoach() {
            $scope.courseInfo.coachId = authService.getUser().userId;
            vm.coachName = 'me';
            vm.coachPopOpen = false;
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