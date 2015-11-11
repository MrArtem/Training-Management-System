(function () {
	'use strict';

	angular
		.module('tmsApp')
		.controller('ManageCourseController', ManageCourseController);

	/** @ngInject */
	function ManageCourseController($scope, $stateParams, courseAPI, userAPI, authService) {

		var vm = this;
		vm.courseInfo = {};
		vm.courseInfo.tagList = []
		vm.exCoachInfo = {};

		vm.approveCourse = approveCourse;
		vm.createCourse = createCourse;
		vm.getEditedCourse = getEditedCourse;
		vm.isAdmin = isAdmin;

		vm.addExCoach = addExCoach;
		vm.getExCoachList = getExCoachList;
		vm.setExCoach = setExCoach;
		vm.setMyselfAsCoach = setMyselfAsCoach;

		if ($stateParams.edit) {
			vm.courseId = $stateParams.courseId;
			vm.actionId = $stateParams.id;
			$scope.isEdited = true;
			vm.isDraft = ($stateParams.type == 'CREATE') ? true : false;
			vm.getEditedCourse();
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
			$scope.courseInfo.language = parseInt($scope.courseInfo.language);

			//if admin is logged, he must choose a coach for training
			if (!vm.isAdmin()) {
				$scope.courseInfo.coachId = authService.getUser().userId;
			}
			courseAPI.createCourse($scope.courseInfo).then(function (data) {
				console.log("request success")
			}); //some then with alert?

			console.log($scope.courseInfo);
		}

		function getEditedCourse() {
			courseAPI.getEditedCourse(vm.actionId).then(function (data) {
					$scope.courseInfo = angular.copy(data);
					console.log($scope.courseInfo);
				}
			);
		}

		function isAdmin() {
			return authService.getAccessRights() == 0 ? true : false;
		}

		///////////////////////////////////////

		function addExCoach() {
			userAPI.addExCoach(vm.exCoachInfo).then(function (result) {
				//save coach //TODO
				console.log('Coach added successfully');
			});
		}

		function getExCoachList() {
			courseAPI.getExCoachList().then(function (data) {
				vm.exCoachList = angular.copy(data);
			});
		}

		function setExCoach(index) {
			var coachId = vm.exCoachList[index].id;
			vm.coachName = vm.exCoachList[index].username;
			vm.courseInfo.coachId = coachId;
		}

		function setMyselfAsCoach() {
			vm.courseInfo.coachId = authService.getUser().userId;
		}
	}
})();