(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('ApprovalController', ApprovalController);

    /** @ngInject */
    function ApprovalController($state, adminAPI, courseAPI) {
        var vm = this;
        vm.approveList = [];
        vm.lessonToApprove = {};
        //var stompClient = null;

        //vm.disconnect = disconnect;
        vm.approveAddLesson = approveAddLesson;
        vm.cancelAddLesson = cancelAddLesson;
        vm.cancelDeleteCourse = cancelDeleteCourse;
        vm.deleteCourse = deleteCourse;
        vm.getApproveList = getApproveList;
        vm.makeText = makeText;
        vm.seeDetails = seeDetails;
        //vm.send = send;
        //vm.connect = connect;

        vm.getApproveList();

        //vm.connect();
        //
        //$scope.$on('$stateChangeStart', function() {
        //    vm.disconnect();
        //})
        //
        //function connect() {
        //    var socket = new SockJS('http://localhost:8080/approve_list');
        //    stompClient = Stomp.over(socket);
        //    stompClient.connect({}, {}, function(frame) {
        //        console.log('Connected: ' + frame);
        //        stompClient.subscribe('/pipe/approve_list', function(greeting){
        //            console.log(greeting);
        //        });
        //
        //        vm.send();
        //    });
        //}

        //function disconnect() {
        //    if (stompClient != null) {
        //        stompClient.disconnect();
        //    }
        //    console.log("Disconnected");
        //}
        //
        //function send() {
        //    debugger;
        //    stompClient.send("/approve_list", {}, JSON.stringify({ 'page': 0, 'page_size': 10 }))
        //}

        function approveAddLesson() {
            courseAPI.approveLesson(vm.actionIdToApprove, vm.lessonToApprove).then(function(data) {
                console.log('Approved lesson adding');
                $('#addLessonModal').modal('hide');
            });
        }

        function cancelAddLesson() {
            courseAPI.cancelLesson(vm.actionIdToApprove).then(function(data) {
                console.log('Canceled lesson adding');
                $('#addLessonModal').modal('hide');
            });
        }

        function cancelDeleteCourse() {
            courseAPI.cancelChange(vm.actionIdToApprove).then(function(data) {
                console.log('Canceled course deleting');
                $('#deleteTrainingModal').modal('hide');
            });
        }

        function deleteCourse() {
            courseAPI.deleteCourse(vm.trainingIdToApprove).then(function(data) {
                $('#deleteTrainingModal').modal('hide');
            });
        }

        function getApproveList() {
            adminAPI.getApproveList().then(function(data) {
                vm.approveList = angular.copy(data);
                console.log('Received approvals: ', vm.approveList);
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

        function seeDetails(item) {
            var type = item.type.toString();
            var tableName = item.tableName;
            switch(type) {
                case 'CREATE':
                    if(tableName == 'APPROVE_TRAINING') {
                        $state.go('managecourse', {courseId: item.trainingId, edit: true, id: item.id, type: type});
                    }
                    else {
                        vm.lessonIdToApprove = item.lessonId;
                        vm.actionIdToApprove = item.id;
                        vm.coachNameToApprove = item.coachName;
                        $('#createLessonModal').modal();
                    }
                    break;
                case 'EDIT':
                    if(tableName == 'APPROVE_TRAINING') {
                        $state.go('managecourse', {courseId: item.trainingId, edit: true, id: item.id, type: type});
                    }
                    else {

                    }
                    break;
                case 'REMOVE':
                    if(tableName == 'APPROVE_TRAINING') {
                        vm.trainingIdToApprove = item.trainingId;
                        vm.trainingNameToApprove = item.trainingTitle;
                        vm.actionIdToApprove = item.id;
                        vm.coachNameToApprove = item.coachName;
                        $('#deleteTrainingModal').modal();
                    }
                    else {

                    }
                default:
                    return '';
            }
        }
    }
})();