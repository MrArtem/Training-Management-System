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
            courseAPI.addParticipant($stateParams.courseId, vm.participantInfo).then(function(data) {
                console.log('participant added successfully');
            });
        }

        function deleteParticipant() {

        }

        function getParticipants() {
            courseAPI.getParticipants($stateParams.courseId).then(function(data) {
                console.log("got participants");
                $scope.$parent.courseInfo.participantsList = angular.copy(data);
            });
        }

    }
})();