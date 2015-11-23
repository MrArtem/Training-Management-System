(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('ParticipantsController', ParticipantsController);

    /** @ngInject */
    function ParticipantsController($scope, $stateParams, courseAPI, userAPI) {
        var vm = this;
        vm.fbInfo = {
            attendance: false,
            attitude: false,
            commSkills: false,
            questions: false,
            motivation: false,
            focusOnResult: false,
            other: ""
        };
        vm.isContentLoaded = false;
        vm.participantInfo = {};

        vm.addFeedbackOnUser = addFeedbackOnUser;
        vm.addParticipant = addParticipant;
        vm.clearInfo = clearInfo;
        vm.deleteParticipant = deleteParticipant;
        vm.getParticipants = getParticipants;
        vm.getUserId = getUserId;

        vm.getParticipants();

        function addFeedbackOnUser() {
            console.log('Feedback to be added:');
            console.log(vm.fbInfo);
            userAPI.addFeedbackOnUser($stateParams.courseId, vm.selectedUserId, vm.fbInfo).then(function(data) {
                console.log('feedback added successfully');
            });
        }

        function addParticipant() {
            console.log("Participant to be added: ");
            console.log(vm.participantInfo);
            courseAPI.addParticipant($stateParams.courseId, vm.participantInfo).then(function(data) {
                console.log('participant added successfully');
            });
        }

        function clearInfo() {
            $scope.participantForm.$setUntouched();
            vm.participantInfo = {};
        }

        function deleteParticipant(index) {
            var userId = $scope.$parent.courseInfo.participantsList[index].id;
            courseAPI.deleteParticipant($stateParams.courseId, userId).then(function(data) {
                //save new participants list
                console.log("Participant with id " + userId + " deleted successfully");
            });
        }

        function getParticipants() {
            vm.isContentLoaded = false;
            courseAPI.getParticipants($stateParams.courseId).then(function(data) {
                console.log("got participants");
                $scope.$parent.courseInfo.participantsList = angular.copy(data);
                vm.isContentLoaded = true;
            });
        }

        function getUserId(index) {
            vm.clearInfo();
            vm.selectedUserId = $scope.$parent.courseInfo.participantsList[index].userId;
        }

    }
})();