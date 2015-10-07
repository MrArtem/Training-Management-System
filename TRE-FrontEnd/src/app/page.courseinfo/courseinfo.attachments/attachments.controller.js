(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('AttachmentsController', AttachmentsController);

    /** @ngInject */
    function AttachmentsController() {
        var vm = this;
        vm.attachFiles = attachFiles;
        vm.deleteFile = deleteFile;
        vm.getAttachments = getAttachments;

        function attachFiles() {

        }

        function deleteFile() {

        }

        function getAttachments($scope, courseAPI) {
            //courseAPI.getAttachments().then(function(data) {
            //    $scope.$parent.saveAttachments(data);
            //});
        }
    }
})();