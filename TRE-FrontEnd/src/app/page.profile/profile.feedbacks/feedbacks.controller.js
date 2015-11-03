(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('FeedbacksController', FeedbacksController);

    /** @ngInject */
    function FeedbacksController($scope, $stateParams, userAPI) {
        var vm = this;
        vm.fbInfo = {};

        vm.getFeedbacks = getFeedbacks;
        vm.seeFeedback = seeFeedback;

        vm.getFeedbacks();

        function getFeedbacks() {
            userAPI.getFeedbacksOn($stateParams.userId).then(function(feedbacks) {
                $scope.$parent.profileInfo.feedbacks = angular.copy(feedbacks);
                console.log("Received feedbacks: ");
                console.log($scope.$parent.profileInfo.feedbacks);
            });
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