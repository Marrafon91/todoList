import axios from 'axios';
import { api } from '../utils/api';

api.interceptors.request.use(
  (config) => {
    console.log('Requisição:', config.url);

    return config;
  },

  (error) => {
    return Promise.reject(error);
  },
);

api.interceptors.response.use(
  (response) => {
    console.log('Resposta:', response.status);

    return response;
  },

  (error) => {
    if (axios.isAxiosError(error)) {
      switch (error.response?.status) {
        case 400:
          console.error('400 - Requisição inválida');
          break;

        case 401:
          console.error('401 - Não autorizado');
          break;

        case 403:
          console.error('403 - Acesso negado');
          break;

        case 404:
          console.error('404 - Recurso não encontrado');
          break;

        case 422:
          console.error('422 - Erro de validação');
          break;

        case 500:
          console.error('500 - Erro interno');
          break;

        default:
          console.error(error.message);
      }
    }

    return Promise.reject(error);
  },
);
