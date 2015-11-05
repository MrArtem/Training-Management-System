(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('FeedbacksController', FeedbacksController);

    /** @ngInject */
    function FeedbacksController($scope, $stateParams, userAPI) {
        var vm = this;
        vm.getFeedbacks = getFeedbacks;

        vm.getFeedbacks();

        function getFeedbacks() {
            userAPI.getFeedbacksOn($stateParams.userId).then(function(feedbacks) {
                $scope.$parent.profileInfo.feedbacks = angular.copy(feedbacks);
                console.log("Received feedbacks: ");
                console.log($scope.$parent.profileInfo.feedbacks);
            });
        }
    }
})();