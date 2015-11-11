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
        vm.participantInfo = {};

        vm.addFeedbackOnUser = addFeedbackOnUser;
        vm.addParticipant = addParticipant;
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

        function deleteParticipant(index) {
            var userId = $scope.$parent.courseInfo.participantsList[index].id;
            courseAPI.deleteParticipant($stateParams.courseId, userId).then(function(data) {
                //save new participants list
                console.log("Participant with id " + userId + " deleted successfully");
            });
        }

        function getParticipants() {
            courseAPI.getParticipants($stateParams.courseId).then(function(data) {
                console.log("got participants");
                $scope.$parent.courseInfo.participantsList = angular.copy(data);
            });
        }

        function getUserId(index) {
            vm.selectedUserId = $scope.$parent.courseInfo.participantsList[index].userId;
        }

    }
})();