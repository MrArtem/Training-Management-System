(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('ParticipantsController', ParticipantsController);

    /** @ngInject */
    function ParticipantsController($scope, $stateParams, courseAPI) {
        var vm = this;
        vm.participantInfo = {};

        vm.addParticipant = addParticipant;
        vm.deleteParticipant = deleteParticipant;
        vm.getParticipants = getParticipants;

        vm.getParticipants();

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

    }
})();