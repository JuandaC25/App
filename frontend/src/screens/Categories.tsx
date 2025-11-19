import React, { useEffect, useState } from 'react';
import {View, Text, FlatList, TouchableOpacity, Alert, TextInput, Modal, ActivityIndicator, ScrollView} from 'react-native';
import {categoriesSyles} from '../styles/CategoriesStyles';
import { categoryService, authService } from '../services/api';

export default function CategoriesScreen(){
    const [categories, setCategories] = useState<any[]>([]);
    const [loading, setLoading] = useState(false);
    const [modalVisible, setModalVisible] = useState(false);
    const [editing, setEditing] = useState<any>(null);
    const [fromData, setFormData] = useState({name: '', description: ''});
    const [error, setError] = useState('');
    const [currentUser,setCurrentUser] = useState<any>(null);

    useEffect(() => {
        loadCurrentUser();
        loadCategories();
    }, []);

    const loadCurrentUser = async () => {
        try{
            const user = await authService.getCurrentUser();
            setCurrentUser(user);
                } catch (error) {
                    console.error("error  al cargar usuarios", error);
                }
            };

            const loadCategories = async () => {
                setLoading(true);
                setError('');
                try{
                    const response = await categoryService.getAll();
                    setCategories(response.data || []);
                }catch (error){
                    setError("Error al cargar las categorias");
                    setCategories([]);
                } finally {
                    setLoading(false);
                }
            };

            const resetForm = () => {
                setFormData({ name: '', description: ''});
                setEditing(null);
            }

            const handlesave = async () => {
                if (!fromData.name.trim()){
                    Alert.alert("Error","El nombre es obligatorio ");
                    return;
                }
                try{
                    if (editing){
                        await categoryService.update(editing.id, fromData);
                        Alert.alert("Exito", "Categoria actualizada");
                    }else{
                        await categoryService.create(fromData);
                        Alert.alert("Exito", "Categoria creada correctamente")
                    }
                    setModalVisible(false);
                    resetForm();
                    loadCategories();
                } catch (error){
                    Alert.alert("Error", "No se pudo guardar");
                }
            };

            const handleDelete = (item : any) => {
                if (currentUser?.role !== "admin"){
                    Alert.alert("Acceso denegado", "solo administradores pueden eliminar categorias");
                    return;
                }
                Alert.alert("Confirmar","¿Eliminar ${item.name}?", [
                    {text: 'Cancelar', style: 'cancel'},
                    {text: 'Eliminar',
                        style: 'destructive',
                        onPress: async () => {
                            try{

                            }
                        }
                    }
                ] )
            }
}