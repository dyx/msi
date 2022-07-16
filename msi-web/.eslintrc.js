module.exports = {
	root: true,
	env: {
		node: true
	},
	extends: ['plugin:vue/vue3-recommended', 'eslint:recommended', 'prettier'],
	parserOptions: {
		ecmaVersion: 2020
	},
	rules: {
		'no-unused-vars': [
			'error',
			{ args: 'none' }
		],
		'no-useless-escape': 'off',
		'no-constant-condition': 'off',
		'vue/multi-word-component-names': 'off',
		'vue/attributes-order': 'off',
		'vue/require-prop-type-constructor': 'off',
		'vue/no-v-html': 'off'
	}
}
