(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('CommentsController', CommentsController);

    /** @ngInject */
    function CommentsController($scope, $stateParams, courseAPI) {
        var vm = this;
        vm.commentInfo = {
            clear: false,
            creativity: false,
            effective: 0,
            interesting: false,
            newMaterial: false,
            recommendation: false
        };

        vm.addComment = addComment;
        vm.deleteCommment = deleteComment;
        vm.getComments = getComments;

        vm.getComments();

        function addComment() {
            console.log("Comment to be added: ");
            console.log(vm.commentInfo);
            courseAPI.addComment($stateParams.courseId, vm.commentInfo).then(function(data) {
                console.log("Comment added successfully");
                vm.commentInfo = {};
            });
        }

        function deleteComment(index) {

        }

        function getComments() {
            courseAPI.getComments($stateParams.courseId).then(function(data) {
                $scope.$parent.courseInfo.commentList = angular.copy(data);
                console.log('Received comments: ');
                console.log($scope.$parent.courseInfo.commentList);
            });
        }
    }
})();