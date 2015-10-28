(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('AttachmentsController', AttachmentsController);

    /** @ngInject */
    function AttachmentsController($scope, courseAPI, fileReader) {
        var vm = this;
        vm.attachFiles = attachFiles;
        vm.addFile = addFile;
        vm.deleteFile = deleteFile;
        vm.getAttachments = getAttachments;

        $scope.progress = 0;
        $scope.getFile = function () {
            $scope.progress = 0;
            fileReader.readAsDataUrl($scope.file, $scope).then(function (result) {
                $scope.$parent.courseInfo.file.data = result;
                //$scope.courseInfo.file.link = "";
                $scope.courseInfo.file.name = ($scope.file.webkitRelativePath || $scope.file.name);
            });
        };
        $scope.$on("fileProgress", function (e, progress) {
            $scope.progress = progress.loaded / progress.total;
        });

        $scope.nullProgress = function () {
            $scope.progress = 0;
        };



        function addFile() {
            console.log($scope.courseInfo.file)
            courseAPI.uploadFile($scope.courseInfo.file).then(function(result) {
                console.log('File uploaded successfully');
            });
        }

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