(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('FeedbacksController', FeedbacksController);

    /** @ngInject */
    function FeedbacksController($scope, $stateParams, userAPI) {
        var vm = this;
        vm.fbInfo = {};

        vm.clearFbInfo = clearFbInfo;
        vm.getFeedbacks = getFeedbacks;
        vm.leaveFeedback = leaveFeedback;
        vm.seeFeedback = seeFeedback;

        vm.getFeedbacks();

        function clearFbInfo() {
            vm.fbInfo = {};
        }

        function getFeedbacks() {
            userAPI.getFeedbacksOn($stateParams.userId).then(function(feedbacks) {
                $scope.$parent.profileInfo.feedbacks = angular.copy(feedbacks);
                console.log("Received feedbacks: ");
                console.log($scope.$parent.profileInfo.feedbacks);
            });
        }

        function leaveFeedback() {
            console.log('Feedback to be added:');
            console.log(vm.fbInfo);
            userAPI.addFeedbackOnUser($stateParams.courseId, $stateParams.userId, vm.fbInfo).then(function(data) {
                console.log('feedback added successfully');
            }).finally(vm.clearFbInfo);
        }

        function seeFeedback(index) {
            var fbId = $scope.$parent.profileInfo.feedbacks[index].feedbackID;
            console.log(fbId);
            userAPI.getFeedback(fbId).then(function(data) {
                vm.fbInfo = angular.copy(data);
                console.log('Got feedback successfully');
            });
        }
    }
})();