(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('ParticipantsController', ParticipantsController);

    /** @ngInject */
    function ParticipantsController($scope, courseAPI) {
        var vm = this;
        vm.addParticipant = addParticipant;
        vm.deleteParticipant = deleteParticipant;
        vm.getParticipants = getParticipants;

        vm.getParticipants();

        function addParticipant() {

        }

        function deleteParticipant() {

        }

        function getParticipants() {
            //courseAPI.getParticipants().then(function(data) {
            //    $scope.$parent.saveParticipants(data);
            //});
        }

    }
})();