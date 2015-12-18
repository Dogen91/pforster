'use strict';

pforsterControllers.controller('QuestionCtrl', ['$scope', '$compile', 'QuestionService', 'CategoryService', function ($scope, $compile, QuestionService, CategoryService) {
	
	$scope.activeTab = 'simple';
	
	$scope.simpleQuestion = {};
	$scope.imageQuestion = {};
	$scope.complementQuestion = {answers: []};
	$scope.answerIndex = 3;

	CategoryService.getCategories().then(function(categories) {
		$scope.categories = categories;
	});
	
	$scope.saveQuestion = function(question, type){
		QuestionService.saveQuestion(question, type).then(function(savedQuestion){
			$scope.simpleQuestion = {};
			$scope.simpleQuestion.category = savedQuestion.category;
			$scope.imageQuestion = {};
			$scope.imageQuestion.category = savedQuestion.category;
			$scope.complementQuestion = {answers: []};
			$scope.complementQuestion.category = savedQuestion.category;
		});
	};
	
	$scope.addAnswer = function(){
		var elem = document.getElementById('answers');
		var answerElem = $compile("<pfo-answer index=" + $scope.answerIndex++ +" label=" + $scope.answerIndex +" question=\"complementQuestion\"></pfo-answer>")($scope);
		angular.element(elem).append(answerElem);
	}
	
}]);