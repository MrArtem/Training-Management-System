(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('AttachmentsController', AttachmentsController);

    /** @ngInject */
    function AttachmentsController($scope, $stateParams, courseAPI, fileReader) {
        //debugger;
        var vm = this;
        $scope.filesToUpload = [];

        vm.deleteFile = deleteFile;
        vm.getAttachments = getAttachments;
        vm.uploadFiles = uploadFiles;

        vm.getAttachments();

        function uploadFiles() {
            console.log('files to be uploaded: ', $scope.filesToUpload);
            courseAPI.uploadFiles($stateParams.courseId, $scope.filesToUpload).then(function(result) {
                $scope.$parent.courseInfo.files = angular.copy(result);
                console.log($scope.$parent.courseInfo.files);
            });
        }

        function deleteFile(index) {
            var fileId = $scope.$parent.courseInfo.files[index].idFile;
            console.log('Id of file to be deleted: ' + fileId);
            courseAPI.deleteFile(fileId).then(function(result) {
                $scope.$parent.courseInfo.files = angular.copy(result);
                console.log('File deleted successfully');
            });
        }

        function getAttachments() {
            courseAPI.getAttachments($stateParams.courseId).then(function(data) {
                $scope.$parent.courseInfo.files = angular.copy(data);
                console.log('Received attachments: ', data);
            });
        }
    }
})();