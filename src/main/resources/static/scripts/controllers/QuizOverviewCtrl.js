'use strict';

pforsterControllers.controller('QuizOverviewCtrl', ['$scope', 'QuestionService', 'CategoryService', function ($scope, QuestionService, CategoryService) {

	CategoryService.getCategories().then(function(categories) {
		$scope.categories = categories;
	});
	
}]);