(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('CommentsController', CommentsController);

    /** @ngInject */
    function CommentsController($scope, courseAPI) {
        var vm = this;
        vm.getComments = getComments;

        function getComments() {
            //courseAPI.getComments().then(function(data) {
            //    $scope.$parent.saveComments(data);
            //});
        }
    }
})();