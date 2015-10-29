(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('AttachmentsController', AttachmentsController);

    /** @ngInject */
    function AttachmentsController($scope, courseAPI, fileReader) {
        //debugger;
        var vm = this;
        $scope.filesToUpload = [];

        vm.deleteFile = deleteFile;
        vm.getAttachments = getAttachments;
        vm.uploadFiles = uploadFiles;

        function uploadFiles() {
            console.log($scope.filesToUpload);
            courseAPI.uploadFiles($scope.filesToUpload).then(function(result) {
                console.log($scope.$parent.courseInfo.files);
            });
        }

        function deleteFile(index) {

        }

        function getAttachments($scope, courseAPI) {
            //courseAPI.getAttachments().then(function(data) {
            //    $scope.$parent.saveAttachments(data);
            //});
        }
    }
})();