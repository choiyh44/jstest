(function() {
	var app = angular.module('restApp', []);

	app.controller('RestController', ["$scope", "restService", function($scope, restService) {
		// 폼필드 초기화
		$scope.communityId = '';
		$scope.communityName = '';

		// 입력필드에 값입력 처리
		$scope.onPostFormSubmit = function () {
			
			// 입력필드에 값이 있는 경우만 처리
			if (!$scope.communityId.length || !$scope.communityName) {
				return;
			}

			// 입력필드값을 todos 리스트에 저장
			var newPost = {communityId: $scope.communityId ,communityName: $scope.communityName};

			// DB에 등록
			restService.addPost(newPost);

		};


	}]);

})();
