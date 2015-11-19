(function () {
	'use strict';

	angular
		.module('tmsApp')
		.controller('DatesController', DatesController);

	/** @ngInject */
	function DatesController($scope, $state, $stateParams, userAPI) {
		var vm = this;
		vm.addDate = addDate;
		vm.changeTab = changeTab;

		vm.dates = [];
		vm.days = ['M', 'T', 'W', 'Th', 'F', 'Sa', 'S'];
		vm.onDays = [false, false, false, false, false, false, false];

		for(var i = 0; i < 7; i++) {
			$scope.tempDates.push({hours: undefined, minutes: undefined, place: ""});
		}

		function addDate() {
			$scope.courseInfo.lessonList.push({
				date: (new Date(vm.tempDate)).getTime()
			});
		}

		function changeTab() {
			if ($scope.courseInfo.isRepeating) {
				$state.go('managecourse.dates.repeat');
			}
			else {
				$state.go('managecourse.dates.manual');
			}
		}
	}
})();