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


        //NEED TO RELOAD FILES
        function uploadFiles() {
            console.log('files to be uploaded: ', $scope.filesToUpload);
            courseAPI.uploadFiles($stateParams.courseId, $scope.filesToUpload).then(function(result) {
                //$scope.$parent.courseInfo.files = angular.copy(result); UNCOMMENT ONLY WHEN FILES ARE RETURNED ON UPLOAD
                console.log($scope.$parent.courseInfo.files);
            });
        }

        //TODO
        function deleteFile(index) {
            //var fileId = $scope.$parent.courseInfo.files[index].id; //field name id?
            //console.log('Id of file to be deleted: ' + fileId);
            //courseAPI.deleteFile(fileId).then(function(data) {
            //    console.log('File deleted successfully');
            //});
        }

        function getAttachments() {
            courseAPI.getAttachments($stateParams.courseId).then(function(data) {
                $scope.$parent.courseInfo.files = angular.copy(data);
                console.log('Received attachments: ', data);
            });
        }
    }
})();