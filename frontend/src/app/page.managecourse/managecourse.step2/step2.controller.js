(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('Step2Controller', Step2Controller);

    /** @ngInject */
    function Step2Controller($scope, courseAPI) {
        var vm = this;
        vm.tagList = [];
        vm.selectedTags = [];

        vm.addTag = addTag;
        vm.addTagToList = addTagToList;
        vm.getTagList = getTagList;
        vm.isTagSelected = isTagSelected;
        vm.removeTag = removeTag;

        vm.getTagList();

        //TODO
        function addTag(tagSpecialty) {
            var tag = {
                specialty: tagSpecialty
            };
            courseAPI.addTag(tag).then(function(data) {
                //vm.tagList = angular.copy(data);
                console.log('Tag added successfully');
            })
        }

        function addTagToList(tag) {
            var tagIndex = vm.selectedTags.map(function(tag) {
                return tag.id;
            }).indexOf(tag.id);
            if(tagIndex > -1) {
                vm.selectedTags.splice(tagIndex, 1);
                $scope.parent.courseInfo.tagList.splice(tagIndex, 1);
            }
            else {
                vm.selectedTags.push(tag);
                $scope.parent.courseInfo.tagList.push(tag.id);
            }
        }

        function getTagList() {
            courseAPI.getAllTags().then(function(data) {
                vm.tagList = angular.copy(data);
                console.log('Received tags: ', data);
            });
        }

        function isTagSelected(tagId) {
            return (vm.selectedTags.map(function(tag) {
                return tag.id;
            }).indexOf(tagId) > -1);
        }

        function removeTag(tag) {
            var tagIndex = vm.selectedTags.indexOf(tag);
            if(tagIndex > -1) {
                vm.selectedTags.splice(tagIndex, 1);
            }
        }
    }
})();