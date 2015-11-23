(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('AttachmentsController', AttachmentsController);

    /** @ngInject */
    function AttachmentsController($scope, $stateParams, courseAPI) {
        //debugger;
        var vm = this;
        vm.data = 'data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7';
        vm.fileName = 'logo.png';
        vm.isContentLoaded = false;
        $scope.filesToUpload = [];

        vm.deleteFile = deleteFile;
        vm.deleteFileFromUpload = deleteFileFromUpload;
        vm.download = download;
        vm.getAttachments = getAttachments;
        vm.uploadFiles = uploadFiles;

        vm.getAttachments();

        function uploadFiles() {
            console.log('files to be uploaded: ', $scope.filesToUpload);
            courseAPI.uploadFiles($stateParams.courseId, $scope.filesToUpload).then(function(result) {
                $scope.$parent.courseInfo.files = angular.copy(result);
                console.log($scope.$parent.courseInfo.files);
            }).finally(function() {
                $scope.filesToUpload = [];
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

        function deleteFileFromUpload(index) {
            $scope.filesToUpload.splice(index, 1);
        }

        function download(fileId, uri, filename) {
            console.log(fileId, uri, filename);
            $("#" + fileId).attr("href", uri).attr("download", filename);
        }


        function getAttachments() {
            vm.isContentLoaded = false;
            courseAPI.getAttachments($stateParams.courseId).then(function(data) {
                $scope.$parent.courseInfo.files = angular.copy(data);
                vm.isContentLoaded = true;
                console.log('Received attachments: ', data);
            });
        }
    }
})();