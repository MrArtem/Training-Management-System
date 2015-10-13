(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('ApprovalController', ApprovalController);

    /** @ngInject */
    function ApprovalController(adminAPI) {
        var vm = this;

        vm.approveList = [];
        vm.getApproveList = getApproveList;
        vm.makeText = makeText;

        vm.approveList.push({
            coachName: 'John Smith',
            date: '14.05.2015',
            tableName: 'APPROVE_TRAINING',
            trainingName: 'Java for beginners',
            type: 'CREATE'
        });

        function getApproveList() {
            adminAPI.getApproveList().then(function(data) {
                vm.approveList = angular.copy(data);
            });
        }

        function makeText(type, tableName) {
            switch(type) {
                case 'CREATE':
                    return (tableName == 'APPROVE_TRAINING') ? ' wants to create training ' : ' wants to add lesson to training ';
                    break;
                case 'EDIT':
                    return (tableName == 'APPROVE_TRAINING') ? ' wants to edit training ' : ' wants to edit lesson in training ';
                    break;
                case 'REMOVE':
                    return (tableName == 'APPROVE_TRAINING') ? ' wants to delete training ' : ' wants to delete lesson from training ';
                default:
                    return 'unknown type';
            }
        }
    }
})();