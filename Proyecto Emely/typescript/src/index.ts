import { ApolloServer } from '@apollo/server';
import { startStandaloneServer } from '@apollo/server/standalone';
import axios from 'axios';
import { Personal } from './models/ipersonal';
import { Rol } from './models/irol';
import { gql } from 'apollo-server-express';

const apiUrl = 'http://localhost:8080'; 

export const typeDefs = gql`
  type Query {
    hello: String
    getAllPersonal: [Personal]
    getPersonalById(id: ID!): Personal
    getAllRoles: [Rol]
    getRolById(id: ID!): Rol
  }

  type Mutation {
    savePersonal(personal: PersonalInput!): Personal
    updatePersonal(id: ID!, personal: PersonalInput!): Personal
    deletePersonalById(id: ID!): String
    saveRol(rol: RolInput!): Rol
    updateRol(id: ID!, rol: RolInput!): Rol
    deleteRolById(id: ID!): String
  }

  input PersonalInput {
    nombre_personal: String
    edad: Int
    direccion: String
    salario: Float
    rol_id: ID
  }

  input RolInput {
    nombreRol: String
    descripcion: String
  }

  type Personal {
    personal_id: ID
    nombre_personal: String
    edad: Int
    direccion: String
    salario: Float
    rol_id: ID
  }

  type Rol {
    rol_id: ID
    nombreRol: String
    descripcion: String
  }
`;

 const rolResolvers = {
  Query: {
      getAllRoles: async () => {
          try {
              const response = await axios.get<Rol[]>(`${apiUrl}/roles`);
              return response.data;
          } catch (error) {
              console.error('Error al obtener los roles:', error);
              throw error;
          }
      },
      getRolById: async (_: any, { id }: { id: string }) => {
          try {
              const response = await axios.get<Rol>(`${apiUrl}/roles/${id}`);
              return response.data;
          } catch (error) {
              console.error(`Error al obtener el rol con ID ${id}:`, error);
              throw error;
          }
      },
  },
  Mutation: {
      saveRol: async (_: any, { rol }: { rol: Rol }) => {
          try {
              const response = await axios.post<Rol>(`${apiUrl}/roles`, rol);
              return response.data;
          } catch (error) {
              console.error('Error al guardar el rol:', error);
              throw error;
          }
      },
      updateRol: async (_: any, { id, rol }: { id: string, rol: Rol }) => {
          try {
              const response = await axios.put<Rol>(`${apiUrl}/roles/${id}`, rol);
              return response.data;
          } catch (error) {
              console.error(`Error al actualizar el rol con ID ${id}:`, error);
              throw error;
          }
      },
      deleteRolById: async (_: any, { id }: { id: string }) => {
          try {
              const response = await axios.delete<string>(`${apiUrl}/roles/${id}`);
              return response.data;
          } catch (error) {
              console.error(`Error al eliminar el rol con ID ${id}:`, error);
              throw error;
          }
      },
  },
};

const personalResolvers = {
  Query: {
      getAllPersonal: async () => {
          try {
              const response = await axios.get<Personal[]>(`${apiUrl}/personal`);
              return response.data;
          } catch (error) {
              console.error('Error al obtener el personal:', error);
              throw error;
          }
      },
      getPersonalById: async (_: any, { id }: { id: string }) => {
          try {
              const response = await axios.get<Personal>(`${apiUrl}/personal/${id}`);
              return response.data;
          } catch (error) {
              console.error(`Error al obtener el personal con ID ${id}:`, error);
              throw error;
          }
      },
  },
  Mutation: {
      savePersonal: async (_: any, { personal }: { personal: Personal }) => {
          try {
              const response = await axios.post<Personal>(`${apiUrl}/personal`, personal);
              return response.data;
          } catch (error) {
              console.error('Error al guardar el personal:', error);
              throw error;
          }
      },
      updatePersonal: async (_: any, { id, personal }: { id: string, personal: Personal }) => {
          try {
              const response = await axios.put<Personal>(`${apiUrl}/personal/${id}`, personal);
              return response.data;
          } catch (error) {
              console.error(`Error al actualizar el personal con ID ${id}:`, error);
              throw error;
          }
      },
      deletePersonalById: async (_: any, { id }: { id: string }) => {
          try {
              const response = await axios.delete<string>(`${apiUrl}/personal/${id}`);
              return response.data;
          } catch (error) {
              console.error(`Error al eliminar el personal con ID ${id}:`, error);
              throw error;
          }
      },
  },
};

        const resolvers = {
          Query: {
            ...personalResolvers.Query,
            ...rolResolvers.Query,
          },
        };

        const server = new ApolloServer({
          typeDefs,
          resolvers,
        });
        

        const { url } = await startStandaloneServer(server, {
          listen: { port: 4000 },
        });
        
        console.log(`🚀  Server ready at: ${url}`);