(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('AttachmentsController', AttachmentsController);

    /** @ngInject */
    function AttachmentsController() {
        var vm = this;
        vm.attachFiles = attachFiles;
        vm.addFile = addFile;
        vm.deleteFile = deleteFile;
        vm.getAttachments = getAttachments;


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