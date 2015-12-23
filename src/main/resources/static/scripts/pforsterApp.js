'use strict';

var pforsterApp = angular.module('pforsterApp', ['ngRoute', 'pforsterControllers', 'pforsterServices', 'pforsterFilters', 'pforsterDirectives', 'ui.bootstrap', 'flow']);

pforsterApp.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/quiz', {
		templateUrl : '/views/quiz/quizOverview.html',
		controller : 'QuizOverviewCtrl'
	});
	$routeProvider.when('/quiz/:categoryId/:mode', {
		templateUrl : '/views/quiz/quiz.html',
		controller : 'QuizCtrl'
	});
	$routeProvider.when('/quiz/insert', {
		templateUrl : '/views/quiz/insert.html',
		controller : 'QuestionCtrl'
	});
	$routeProvider.otherwise({
		redirectTo : '/quiz'
	});
} ]);

var pforsterControllers = angular.module('pforsterControllers', []);
var pforsterServices = angular.module('pforsterServices', []);
var pforsterDirectives = angular.module('pforsterDirectives', []);
var pforsterFilters = angular.module('pforsterFilters', []);
